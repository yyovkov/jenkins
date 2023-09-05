def call(Map conf) {
    // Name: gameliftAliasCreate.groovy
    // Description: Create GameLift alias with provided:
    //    * alias_name
    //    * aws_region
    //    * fleet_arn (fleet_id can be passed with the same parameter name)
    //    * tags

    env.ALIAS_NAME = conf.alias_name
    env.AWS_REGION = conf.aws_region
    env.FLEET_ARN  = conf.fleet_arn
    env.TAGS       = conf.tags

    def aliasArn = powershell(returnStdout: true, script: '''
        $cmdAliasCreate = "& aws --region `"${env:AWS_REGION}`"" + `
            " gamelift create-alias" + `
            " --name `"${env:ALIAS_NAME}`"" + `
            " --routing-strategy Type=SIMPLE,FleetId=`"${env:FLEET_ID}`"" + `
            " --tags `"${env:TAGS}`""

        Write-Host Create Alias command: $cmdAliasCreate

        Invoke-Expression $cmdAliasCreate
    '''

    return aliasArn.trim()
}
