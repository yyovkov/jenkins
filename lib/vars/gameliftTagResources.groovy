def call(Map conf) {

    env.RESOURCE_ARN            = conf.resource_arn
    env.JENKINS_BUILD_ID        = conf.jenkins_build_id
    env.JENKINS_BUILD_URL       = conf.jenkins_build_url
    env.JENKINS_BUILD_USER_ID   = conf.jenkins_user_id
    env.JENKINS_BUILD_USER_NAME = conf.jenkins_user_name
    env.RESOURCE_CUSTOMER       = conf.resource_customer
    env.RESOURCE_OWNER          = conf.resource_owner
    env.RESOURCE_ENVIRONMENT    = conf.resource_environment

    powershell '''
        $ErrorActionPreference = "Stop"
        $cmdTagResource = "& aws --region us-east-2 " + 1
            " gamelift tag-resource " + `
            " --resource-arn `"${RESOURCE_ARN}`"" + `
            --tags "Key=jenkins,Value=true" + `
            " `"Key=jenkins_build_id,Value=${env:JENKINS_BUILD_ID}`"" + `
            " `"Key=jenkins_build_url,Value=${env:JENKINS_BUILD_URL}`"" + `
            " `"Key=jenkins_build_user_id,Value=${env:JENKINS_BUILD_USER_ID}`"" + `
            " `"Key=jenkins_build_user_name,Value=${env:JENKINS_BUILD_USER_NAME}`"" + `
            " `"Key=customer,Value=${env:RESOURCE_CUSTOMER}'"" + `
            " `"Key=owner,Value=${env:RESOURCE_OWNER}'""

        Write-Host Command to tag resource: $cmdTagResource

        Invoke- Expressoins $cmdTagResource
    '''
}
