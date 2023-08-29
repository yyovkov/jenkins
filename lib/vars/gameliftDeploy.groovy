def call(Map conf) {
    echo "GameLift Server Deploying"

    powershell '''
        $cmdUploadBuild = "& aws gamelift upload-build" + `
                            " --name conf.awsBuildName" + `
                            " --build-version conf.buildVersion" + `
                            " --build-root conf.buildFolder" + `
                            " --operating-system conf.serverOS" + `
                            " --region conf.awsRegion"
        Write-Host Upload Build Command: $cmdUploadBuild
    '''
}
