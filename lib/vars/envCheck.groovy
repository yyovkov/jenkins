def call(body) {

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
    def aliasesArnList = powershell(returnStdout: true, script: '''
        $cmcAliasesList = "& aws --region ${env:UE5_AWS_REGION}" + `
            " gamelift list-aliases" + `
            " | ConvertFrom-Json"

        $jsonObj = Invoke-Expression $cmcAliasesList

        $aliasesArnList = ($jsonObj" `
            | Select-Object -ExpandProperty Aliases `
            | Where-Object { $_.Name -eq `"${env:UE5_ALIAS_NAME}`" }).AliasArn


        Write-Output $aliasesArnList
    ''')

    println aliasesArnList.getClass()
    println aliasesArnList

    currentBuild.result = 'ABORTED'
    error("This is just a test and very expected")
}
