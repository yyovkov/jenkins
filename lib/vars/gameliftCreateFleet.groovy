def call(Map conf) {

    // $cmdWrite = "& Write-Output blablabla"
    // $res = Invoke-Expression $cmdWrite
    // echo $res

    env.UE5_AWS_INSTANCE_TYPE  = conf.awsInstanceType
    env.UE5_AWS_REGION         = conf.awsRegion
    env.UE5_BUILD_ID           = conf.buildId // Build id from  gameliftDeploy operation
    env.UE5_RUNTIME_CONFIG     = conf.runTimeConfig
    env.UE5_FLEET_NAME         = conf.fleetName
    env.UE5_INBOUND_PERMS_SRV  = "FromPort=7777,ToPort=7996,IpRange=0.0.0.0/0,Protocol=UDP" 
    env.UE5_INBOUND_PERMS_RDP  = "FromPort=3389,ToPort=3389,IpRange=0.0.0.0/0,Protocol=TCP"
    env.UE5_FLEET_TYPE         = "ON_DEMAND"
    env.UE5_SESSION_PROTECTION = "NoProtection"

    powershell '''
        $cmdCreateFleet = "& aws --region `"${env:UE5_AWS_REGION}`"" + `
            " gamelift create-fleet" + `
            " --name `"${env:UE5_FLEET_NAME}`"" + `
            " --build-id `"${env:UE5_BUILD_ID}`"" + `
            " --ec2-instance-type `"${env:UE5_AWS_INSTANCE_TYPE}`"" + `
            " --ec2-inbound-permissions `"${env:UE5_INBOUND_PERMS_SRV}`"" + `
            " --ec2-inbound-permissions `"${env:UE5_INBOUND_PERMS_RDP}`"" + `
            " --fleet-type `"${env:UE5_FLEET_TYPE}`"" + `
            " --runtime-configuration `"${env:UE5_RUNTIME_CONFIG}`"" + `
            " --new-game-session-protection-policy `"${env:UE5_SESSION_PROTECTION}`""

        Write-Host Create Fleet Command: $cmdCreateFleet

        $res = Invoke-Expression $cmdCreateFLeet
        $fleet_json = echo $res | ConvertFrom-JSON
        $fleet_id = $fleet_json.FleetAttributes.FleetId

        # TODO: Create loop waiting Fleet to became ready
        # $fleet_id
        # $fleet_staus = (aws --region us-east-2 gamelift describe-fleet-attributes --fleet-id ${fleet_id} | ConvertFrom-Json).FleetAttributes.Status
        $fleet_status = "Unknown"
        while(!$fleet_status.Equals("ACTIVE")){
            sleep 60
            $fleet_staus = (aws --region us-east-2 gamelift describe-fleet-attributes --fleet-id ${fleet_id} | ConvertFrom-Json).FleetAttributes.Status
            Write-Host Fleet status: $fleet_status
        }

        Write-Host Get Fleet for Build: $cmdGetFleetsForBuild
    '''
}
