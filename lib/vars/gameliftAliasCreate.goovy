def call(Map conf) {

    env.UE5_ALIAS_NAME = 
    env.UE5_ALIAS_TAGS = 
    env.UE5_AWS_REGION
    env.UE5_FLEET_ID = 

    powershell '''
        $cmdAliasCreate = "& aws --region `"${env:UE5_AWS_REGION}`"" + `
            " gamelift create-alias" + `
            " --name `"${env:ALIAS_NAME}`"" + `
            " --routing-strategy Type=SIMPLE,FleetId=`"${env:FLEET_ID}`"" + `
            " --tags `"${env:UE5_ALIAS_TAGS}`""

        Write-Host Create Alias command: $cmdAliasCreate

        Invoke-Expression $cmdAliasCreate
    '''


}
