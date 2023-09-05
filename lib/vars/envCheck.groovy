def call(body) {
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

        $jsonObj = Invoke-Expression $cmdAliasesList

        $aliasesList = "($jsonObj" + `
            " | Select-Object -ExpandProperty Aliases " + `
            " | Where-Object { $_.Name -eq `"${env.ALIAS_NAME}`" }).AliasArn"

        Write-Output $aliasesList
    ''')

    println aliasesArnList.getClass()
    currentBuild.result = 'ABORTED'
}
