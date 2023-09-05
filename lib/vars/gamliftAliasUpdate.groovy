def call(Map conf) {

    env.ALIAS_AWS_REGION    = conf.aws_region
    env.ALIAS_ID            = conf.alias_id
    env.FLEET_ID            = conf.fleet_id

    powershell '''
        $cmdAliasUpdate = "& aws --region ${env:ALIAS_AWS_REGION}" + `
            " gamelift update-alias" + `
            " --alias-id `"${env:ALIAS_ID}`"" + `
            " --routing-stragegy=Type=SIMNPLE,FleetIid=${FLEET_ID}"

        Write-Host Alias Update Command: $cmdAliasUpdate

        Invoke-Expression $cmdAliasUpdate
    '''
}
