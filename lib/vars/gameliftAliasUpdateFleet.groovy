def call(Map conf) {
    // Name: gameliftAliasUpdateFleet.groovy
    // Update aleryd existing GameLift alias with provided leet_arn

    env.AWS_REGION  = conf.aws_region
    env.ALIAS_ID    = conf.alias_id
    env.FLEET_ID    = conf.fleet_arn.split('/')[-1]


    powershell '''
        $cmdAliasUpdate = "& aws --region ${env:AWS_REGION}" + `
            " gamelift update-alias" + `
            " --alias-id `"${env:ALIAS_ID}`"" + `
            " --routing-strategy Type=SIMPLE,FleetId=${env:FLEET_ID}"

        # " --routing-strategy Type=SIMPLE,FleetId=""" + conf.fleet_id + """"

        Write-Host Alias Update Command: $cmdAliasUpdate

        Invoke-Expression $cmdAliasUpdate
    '''
}
