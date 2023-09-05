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
    def aliasesArn = powershell(returnStdout: true, script: '''
        # $cmcAliasesList = "& aws --region ${env:UE5_AWS_REGION}" + `
        #    " gamelift list-aliases" + `
        #    " | ConvertFrom-Json"
        # 
        # $jsonObj = Invoke-Expression $cmcAliasesList

        $myJsonVar = '{
            "Aliases": [
                {
                    "AliasId": "alias-3edd91bd-33df-4b6e-b53a-21cec1b3a9fa",
                    "Name": "MyFirstAlias",
                    "AliasArn": "arn:aws:gamelift:us-east-2::alias/alias-3edd91bd-33df-4b6e-b53a-21cec1b3a9fa",
                    "RoutingStrategy": {
                        "Type": "SIMPLE",
                        "FleetId": "fleet-6b9bb45f-a5a3-4236-8847-f04f2a813a43"
                    },
                    "CreationTime": "2023-09-05T17:20:32.745000+03:00",
                    "LastUpdatedTime": "2023-09-05T17:20:32.745000+03:00"
                },
                {
                    "AliasId": "alias-2222222222222222222222",
                    "Name": "MyFirstAlias",
                    "AliasArn": "arn:aws:gamelift:us-east-2::alias/alias-3edd91bd-33df-4b6e-b53a-21cec1b3a9fa",
                    "RoutingStrategy": {
                        "Type": "SIMPLE",
                        "FleetId": "fleet-6b9bb45f-a5a3-4236-8847-f04f2a813a43"
                    },
                    "CreationTime": "2023-09-05T17:20:32.745000+03:00",
                    "LastUpdatedTime": "2023-09-05T17:20:32.745000+03:00"
                },
                {
                    "AliasId": "alias-3333333333333333333333",
                    "Name": "MyFirstAlias",
                    "AliasArn": "arn:aws:gamelift:us-east-2::alias/alias-3edd91bd-33df-4b6e-b53a-21cec1b3a9fa",
                    "RoutingStrategy": {
                        "Type": "SIMPLE",
                        "FleetId": "fleet-6b9bb45f-a5a3-4236-8847-f04f2a813a43"
                    },
                    "CreationTime": "2023-09-05T17:20:32.745000+03:00",
                    "LastUpdatedTime": "2023-09-05T17:20:32.745000+03:00"
                }
            ]
        }'

        $jsonObj = $myJsonVar | ConvertFrom-Json

        $aliasesArnList = ($jsonObj `
            | Select-Object -ExpandProperty Aliases `
            | Where-Object { $_.Name -eq "${env:UE5_ALIAS_NAME}" }).AliasArn


        Write-Output $aliasesArnList
    ''')

    // def aliasesArnList = aliasesArn.split('\n')
    def aliasesArnList = []
    if (aliasesArnList.size() < 1) {
        def error_message = "There are " + aliasesArnList.size() + 
            " GameLift  aliases with name" + params.CUSTOMER + 
            " while exactly 1 expected"
        currentBuild.result = 'ABORTED'
        error(error_message)
    }

    error("This is just a test and very expected")
}
