def call(body) {
    // Name: gameliftEnvCheck.groovy
    // Checks reqquirements for running pipeline for building and deploying
    // GameLift for Mytaverse
    //
    // Checking if all required parameters are provided
    // Checks if there is only one alias that should be attached to

    env.UE5_ALIAS_NAME = env.GAMELIFT_ALIAS_NAME
    env.UE5_AWS_REGION = params.AWS_REGION


    if (params.CUSTOMER == "") {
        currentBuild.result = 'ABORTED'
        error("Parameter CUSTOMER must be set")
    }

    if (params.OWNER == "") {
        currentBuild.result = 'ABORTED'
        error("Parameter OWNER must be set")
    }

    def aliasesArn = powershell(returnStdout: true, script: '''
        $cmcAliasesList = "& aws --region ${env:UE5_AWS_REGION}" + `
           " gamelift list-aliases" + `
           " | ConvertFrom-Json"
        
        $jsonObj = Invoke-Expression $cmcAliasesList

        $aliasesArnList = ($jsonObj `
        | Select-Object -ExpandProperty Aliases `
        | Where-Object { $_.Name -eq "${env:UE5_ALIAS_NAME}" }).AliasArn

        Write-Host Aliases are: $aliasesArnList
        Write-Output $aliasesArnList
    ''')

    println "String length: " + aliasesArn.length()

    // def aliasesArnList = aliasesArn.split('\n')
    def aliasesArnList = []

    println "Type of the array is: " + aliasesArnList.getClass()
    println "The length of the array is: " + aliasesArnList.length

    def error_message = "There are " + aliasesArnList.size() + 
        " GameLift aliases with name \"" + params.CUSTOMER + 
        "\" while exactly 1 expected"
    
    if ((aliasesArnList.size() < 1) && (! params.CREATE_GAMELIFT_ALIAS)) {
        currentBuild.result = 'ABORTED'
        error(error_message)
    }

    if (aliasesArnList.size() > 1) {
        currentBuild.result = 'ABORTED'
        error(error_message)
    }

    println "The number of aliases is: " + aliasesArnList.size()
    println "The aliases is: " + aliasesArnList[0]
    currentBuild.result = 'ABORTED'
    error("Should not come to here. Should die earlier")
}
