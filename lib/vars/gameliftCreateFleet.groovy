def call(Map conf) {

    env.UE5_AWS_INSTANCE_TYPE  = conf.awsInstanceType
    env.UE5_AWS_REGION         = conf.awsRegion
    env.UE5_BUILD_ID           = conf.buildArn
    env.UE5_RUNTIME_CONFIG     = conf.runTimeConfig
    env.UE5_FLEET_NAME         = conf.fleetName
    env.UE5_INBOUND_PERMS_SRV  = "FromPort=7777,ToPort=7996,IpRange=0.0.0.0/0,Protocol=UDP" 
    env.UE5_INBOUND_PERMS_RDP  = "FromPort=3389,ToPort=3389,IpRange=0.0.0.0/0,Protocol=TCP"
    env.UE5_FLEET_TYPE         = "ON_DEMAND"
    env.UE5_SESSION_PROTECTION = "NoProtection"

    powershell '''
        $ErrorActionPreference = "Stop"
        $cmdCreateFleet = "& aws --region `"${env:UE5_AWS_REGION}`"" + `
            " gamelift create-fleet" + `
            " --name `"${env:UE5_FLEET_NAME}`"" + `
            " --build-id `"${env:UE5_BUILD_ID}`"" + `
            " --ec2-instance-type `"${env:UE5_AWS_INSTANCE_TYPE}`"" + `
            " --ec2-inbound-permissions `"${env:UE5_INBOUND_PERMS_SRV}`"" + `
            " `"${env:UE5_INBOUND_PERMS_RDP}`"" + `
            " --fleet-type `"${env:UE5_FLEET_TYPE}`"" + `
            " --runtime-configuration `"${env:UE5_RUNTIME_CONFIG}`"" + `
            " --new-game-session-protection-policy `"${env:UE5_SESSION_PROTECTION}`""

        Write-Host Create Fleet Command: $cmdCreateFleet

        $res = Invoke-Expression $cmdCreateFLeet
        $fleet_json = echo $res | ConvertFrom-JSON
        $fleet_arnd = $fleet_json.FleetAttributes.FleetArn

        # Wait the fleet status to became "ACTIVE"
        $fleetFinalStatuses = @("ACTIVE", "ERROR", "DELETING", "TERMINATED")
        $cmdGetFleetStatus = "& aws --region us-east-2 gamelift describe-fleet-attributes --fleet-id ${fleet_arnd} | ConvertFrom-Json"
        $fleet_status = (Invoke-Expression $cmdGetFleetStatus).FleetAttributes.Status
        Write-Host Fleet arn: $fleet_arnd

        while("True"){
            $fleet_status = (Invoke-Expression $cmdGetFleetStatus).FleetAttributes.Status
            Write-Host Fleet status: $fleet_status. Next check in 60 sec.
            if ($fleetFinalStatuses.Contains($fleet_status)) {
                if ($fleet_status.Equals("ACTIVE")) {
                    break StatusActive
                } else {
                    throw "Fleet $fleet_arnd failed with status $fleet_status."
                    exit 5
                }
            }
            sleep 60
        }

        Write-Host Get Fleet for Build: $cmdGetFleetsForBuild
    '''
}
