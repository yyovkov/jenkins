def call(Map conf) {

    env.UE5_AWS_INSTANCE_TYPE   = conf.awsInstanceType
    env.UE5_AWS_REGION          = conf.awsRegion
    env.UE5_BUILD_ID            = conf.buildId // Build id from  gameliftDeploy operation
    env.UE5_RUNTIME_CONFIG      = conf.runTimeConfig
    env.UE5_FLEET_NAME          = conf.fleetName
    env.UE5_INBOUND_PERMISSIONS = "FromPort=7777,ToPort=7996,IpRange=0.0.0.0/0,Protocol=UDP FromPort=3389,ToPort=3389,IpRange=0.0.0.0/0,Protocol=TCP"

    env.UE5_FLEET_TYPE          = "ON_DEMAND"

    powershell '''
        $cmdCreateFleet = "& aws gamelift create-fleet" + `
            " --name `"${env:UE5_FLEET_NAME}`"" + `
            " --build-id `"${env:UE5_BUILD_ID}`"" _+ `
            " --ec2-instance-type `"${env:UE5_AWS_INSTANCE_TYPE}`"" + `
            " --ec2-inbound-permissions `"${env:UE5_INBOUND_PERMISSIONS}`"" + `
            " --fleet-type `"${env:UE5_FLEET_TYPE}`"" + `
            " --runtime-configuration `"${env:UE5_RUNTIME_CONFIG}`"" + `
            " --new-game-session-protection-policy NoProtection" + `
            " --region `"${env:UE5_AWS_REGION}`""

        Write-Host Create Fleet Command: $cmdCreateFleet
    ```
}
