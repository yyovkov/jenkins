def call(Map conf) {
    echo "GameLift Server Deploying"

    env.UE5_BUILD_NAME      = conf.buildName.trim()
    env.UE5_BUILD_VERSION   = conf.buildVersion.trim()
    env.UE5_BUILD_ROOT      = conf.buildFolder.trim()
    env.UE5_SERVER_OS       = conf.serverOS.trim()
    env.UE5_AWS_REGION      = conf.awsRegion.trim()

    // def buildArn = powershell(returnStdout: true, script: '''
    def buildArn = powershell(returnStdout: true, script: '''
        $ErrorActionPreference = "Stop"
        $cmdUploadBuild = "& aws gamelift upload-build" + `
            " --name `"${env:UE5_BUILD_NAME}`"" + `
            " --build-version `"${env:UE5_BUILD_VERSION}`"" + `
            " --build-root `"${env:UE5_BUILD_ROOT}`"" + `
            " --operating-system `"${env:UE5_SERVER_OS}`"" + `
            " --region `"${env:UE5_AWS_REGION}`""
            
        Write-Host Upload Build Command: $cmdUploadBuild

        $res = Invoke-Expression $cmdUploadBuild
        # Pringint the full output is long and not very helpful, so commented
        # Write-Host $res 
        $build_id =  (echo $res | Select-String -Pattern "^Build Id:").ToString().Split(" ")[-1]
        # Change return value to build arn (build_arn)
        $cmdBuildDescribe = "& aws --region us-east-2" + `
            " gamelift describe-build" + `
            " --build-id $build_id" + `
            " --output json | ConvertFrom-Json"
        $build_arn = (Invoke-Expression $cmdBuildDescribe).Build.BuildArn
        Write-Host Build ARN: $build_arn

        # TODO: Replace below viktorism with check of describe-build
        Write-Host "Wait for 5 sec to move build state to READY"
        sleep(5)

        # The value assigned to Write-Output will be saved as Groovy variable
        Write-Output $build_arn
    ''')

    return buildArn.trim()
}
