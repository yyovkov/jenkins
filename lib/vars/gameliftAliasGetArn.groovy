def call(Map conf) {
    // Name: gameliftAliasGetArn.groovy
    // Retrives AWS GamLift AliasID based on Alias Name

    env.UE5_AWS_REGION    = conf.aws_region
    env.UE5_ALIAS_NAME    = conf.alias_name

    def aliasesArn = powershell(returnStdout: true, script: '''
        $cmdAliasesList = "& aws --region ${env:UE5_AWS_REGION}" + `
           " gamelift list-aliases" + `
           " | ConvertFrom-Json"
        
        $jsonObj = Invoke-Expression $cmdAliasesList

        $aliasesArnList = ($jsonObj `
        | Select-Object -ExpandProperty Aliases `
        | Where-Object { $_.Name -eq "${env:UE5_ALIAS_NAME}" }).AliasArn

        Write-Output $aliasesArnList
    ''')

    def aliasesArnList = aliasesArn.split('\n')

    if (aliasesArnList.size() != 1) {
        def error_message = "There are " + aliasesArnList.size() + 
            " GameLift aliases with name \"" + params.CUSTOMER + 
            "\" while exactly 1 expected."
        currentBuild.result = 'FAILURE'
        error(error_message)
    }

    return aliasesArnList[0].trim()
}
