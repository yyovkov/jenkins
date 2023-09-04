def call(Map conf) {

    env.RESOURCE_ARN            = conf.resource_arn
    env.UE5_BUILD_TAGS         = conf.buildTags.trim()

    powershell '''
        $ErrorActionPreference = "Stop"
        $cmdTagResource = "& aws --region us-east-2 " + `
            " gamelift tag-resource " + `
            " --resource-arn `"${env:RESOURCE_ARN}`"" + `
            " --tags ${env:UE5_BUILD_TAGS}"

        Write-Host Command to tag resource: $cmdTagResource
        Invoke-Expression $cmdTagResource
    '''
}
