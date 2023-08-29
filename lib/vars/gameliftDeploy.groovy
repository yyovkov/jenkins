def call(Map conf) {
    echo "GameLift Server Deploying"

    env.UE5_BUILD_NAME      = conf.buildName
    env.UE5_BUILD_VERSION   = conf.buildVersion
    env.UE5_BUILD_ROOT      = conf.buildFolder
    env.UE5_SERVER_OS       = conf.serverOS
    env.UE5_AWS_REGION      = conf.awsRegion

    powershell '''
        dir env: 
        $cmdUploadBuild = "& aws gamelift upload-build" + `
                            " --name `"${env:UE5_BUILD_NAME}`"" + `
                            " --build-version `"${env:UE5_BUILD_VERSION}`"" + `
                            " --build-root `"${env:UE5_BUILD_ROOT}`"" + `
                            " --operating-system `"${env:UE5_SERVER_OS}`"" + `
                            " --region `"${env:UE5_AWS_REGION}`""
        Write-Host Upload Build Command: $cmdUploadBuild
    '''
}
