def call(Map conf) {
    echo "GameLift Server Deploying"

    

    powershell '''
        dir env:
        $cmdUploadBuild = "& aws gamelift upload-build" + `
                            " --name ${conf.buildName}" + `
                            " --build-version conf.buildVersion" + `
                            " --build-root conf.buildFolder" + `
                            " --operating-system conf.serverOS" + `
                            " --region conf.awsRegion"
        Write-Host Upload Build Command: $cmdUploadBuild
    '''
}
