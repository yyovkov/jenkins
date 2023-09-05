def call(body) {
    // Name: gameliftEnvCheck.groovy
    // Checks reqquirements for running pipeline for building and deploying
    // GameLift for Mytaverse
    //
    // Checking if all required parameters are provided
    // Checks if there is only one alias that should be attached to

    env.UE5_ALIAS_NAME = params.CUSTOMER
    env.UE5_AWS_REGION = params.AWS_REGION


    if (params.CUSTOMER == "") {
        currentBuild.result = 'ABORTED'
        error("Parameter CUSTOMER must be set")
    }

    if (params.OWNER == "") {
        currentBuild.result = 'ABORTED'
        error("Parameter OWNER must be set")
    }

    // TODO: Check if Alias exists
    def aliasesArn = powershell(returnStdout: true, script: '''
        $cmcAliasesList = "& aws --region ${env:UE5_AWS_REGION}" + `
           " gamelift list-aliases" + `
           " | ConvertFrom-Json"
        
        $jsonObj = Invoke-Expression $cmcAliasesList

        $aliasesArnList = ($jsonObj `
        | Select-Object -ExpandProperty Aliases `
        | Where-Object { $_.Name -eq "${env:UE5_ALIAS_NAME}" }).AliasArn

        Write-Output $aliasesArnList
    ''')

    def aliasesArnList = aliasesArn.split('\n')

    if (aliasesArnList.size() != 1) {
        def error_message = "There are " + aliasesArnList.size() + 
            " GameLift aliases with name \"" + params.CUSTOMER + 
            "\" while exactly 1 expected"
        currentBuild.result = 'ABORTED'
        error(error_message)
    }
}
