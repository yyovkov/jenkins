def call(Map conf) {
    echo "GameLift Server Deploying"

    env.UE5_BUILD_NAME      = conf.buildName
    env.UE5_BUILD_VERSION   = conf.buildVersion
    env.UE5_BUILD_ROOT      = conf.buildFolder
    env.UE5_SERVER_OS       = conf.serverOS
    env.UE5_AWS_REGION      = conf.awsRegion

    // def buildId = powershell(returnStdout: true, script: '''
    def buildId = powershell(returnStdout: true, script: '''
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
        Write-Host Build ID: $build_id

        # TODO: Replace below viktorism with check of describe-build
        Write-Host "Wait for 5 sec to move build state to READY"
        sleep(5)

        # return $build_id
        Write-Output $build_id
    ''')

    return buildId.trim()
}
