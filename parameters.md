# Parameters in the Pipelines

## Pipeline: Labs

``` groovy
booleanParam(name: 'DeployToGameLift', defaultValue: true, description: 'Deploy GameLift server?')
booleanParam(name: 'UseGameCastClient', defaultValue: false, description: 'Use GameCast as streaming platform')
booleanParam(name: 'UseLinuxServer', defaultValue: true, description: 'Use Linux for server')
booleanParam(name: 'UsePureWebClient', defaultValue: true, description: 'Deploy to Pureweb')

string(name: 'AWS_CONCURRENT_EXECUTIONS', defaultValue: '2')
string(name: 'AWS_REGION', defaultValue: 'us-east-2')
string(name: 'ALIAS_ID', defaultValue: 'alias-b365ee1f-5fe9-48cb-bc7d-862767229abc')
string(name: 'CLIENT_BUILD_DIR', defaultValue: 'J:\\JenkinsJobs\\Mytaverse_Client_LabEvent')
string(name: 'MAPS_CONFIG_NAME', defaultValue: 'GameCastTest')
string(name: 'MODEL_NAME', defaultValue: 'LabEvent')
string(name: 'MYTA_BRANCH', defaultValue: '/Main/LabsYovko')
string(name: 'MYTA_CHANGESET', defaultValue: '')
string(name: 'SERVER_BUILD_DIR', defaultValue: 'J:\\JenkinsJobs\\Mytaverse_Server_LabEvent')

choice(name: 'AWS_INSTANCE_TYPE', choices: ['c5.large', 'c5.xlarge', 'c5.2xlarge'])
choice(name: 'CLIENT_CLEANUP', choices: ['STANDARD', 'MINIMAL', 'FULL', 'DELETE'])
choice(name: 'CLIENT_CONFIG', choices: ['Development', 'Shipping'])
choice(name: 'PW_PATH', choices: ['C:\\Users\\builder\\AppData\\Roaming\\npm\\pw.cmd', 'C:\\Users\\naked\\AppData\\Roaming\\npm\\pw.cmd'])
choice(name: 'SERVER_CONFIG', choices: ['Development', 'Shipping'])
choice(name: 'SERVER_CLEANUP', choices: ['STANDARD', 'MINIMAL', 'FULL', 'DELETE'])
choice(name: 'STREAM_CLASS', choices: ['gen4a_ultra', 'gen4a_high', 'gen4n_ultra', 'gen4n_high'])

string(name: 'DESIRED_CAPACITY', defaultValue: '0') // 4
string(name: 'EVENT_ID', defaultValue: '8849675223997682')
string(name: 'GAMECAST_AWS_REGION', defaultValue: 'us-east-2')
```

## Pipeline: DevGit

* Retrived from `Pipeline: Labs`

``` groovy
booleanParam(name: 'NotifySlack', defaultValue: true, description: '')
booleanParam(name: 'DeployToGameLift', defaultValue: true, description: 'Deploy GameLift server?')
booleanParam(name: 'UseLinuxServer', defaultValue: true, description: 'Use Linux for server')
booleanParam(name: 'UseLinuxClient', defaultValue: false, description: 'Use Linux for client')
booleanParam(name: 'UsePureWebClient', defaultValue: true, description: 'Deploy to Pureweb')
booleanParam(name: 'UseGameCastClient', defaultValue: false, description: 'Deploy GameCast for client')

choice(name: 'AWS_CREDS', choices: ['aws-jenkins-dev', 'aws-jenkins-prod'])
choice(name: 'AWS_INSTANCE_TYPE', choices: ['c5.2xlarge', 'c5.xlarge', 'c5.large'])
choice(name: 'CLIENT_CLEANUP', choices: ['STANDARD', 'MINIMAL', 'FULL', 'DELETE'])
choice(name: 'CLIENT_CONFIG', choices: ['Development', 'Shipping', 'DebugGame'])
choice(name: 'SERVER_CONFIG', choices: ['Development', 'Shipping'])
choice(name: 'STREAM_CLASS', choices: ['gen4a_ultra', 'gen4a_high', 'gen4n_ultra', 'gen4n_high'])
choice(name: 'TARGET_CONNECTION', choices: ['CC_Dev', 'CC_Prod'])

string(name: 'ALIAS_ID', defaultValue: 'alias-56aeab7d-133f-49d3-929f-057621c33159')
string(name: 'AWS_CONCURRENT_EXECUTIONS', defaultValue: '4')
string(name: 'AWS_REGION', defaultValue: 'us-east-1')
choice(name: 'CHANGESET_TYPE', choices: ['latest-or-specified', 'latest-stable'])
string(name: 'CLIENT_BUILD_DIR', defaultValue: 'C:\\JenkinsJobs\\Mytaverse_Client')
string(name: 'CLIENT_BUILD_FLAGS', defaultValue: '-build -cook -package -stage -archive -pak -CrashReporter -target=Mytaverse')
string(name: 'CLIENT_UE_ENGINE', defaultValue: 'C:\\UE_Source\\UE_5.1')
string(name: 'DESIRED_CAPACITY', defaultValue: '0') // 5
string(name: 'EVENT_ID', defaultValue: '6996880367489758')
string(name: 'GAMECAST_AWS_REGION', defaultValue: 'us-east-2')
string(name: 'MAPS_CONFIG_NAME', defaultValue: '51Devtest')
string(name: 'MODEL_NAME', defaultValue: 'Mytaverse51Dev')
string(name: 'MYTA_BRANCH', defaultValue: '/Main')
string(name: 'MYTA_CHANGESET', defaultValue: '')
string(name: 'PW_PATH', defaultValue: 'C:\\Users\\builder\\AppData\\Roaming\\npm\\pw.cmd')
string(name: 'PW_CLIENT_ID', defaultValue: 'd2f605c848df776f341e7c24')
string(name: 'PW_CLIENT_SECRET', defaultValue: '3c2bc7fe9a1670ba09e8f3771fa1562c4f304d1a3591f616')
string(name: 'SERVER_BUILD_DIR', defaultValue: 'C:\\JenkinsJobs\\Mytaverse_Server')
string(name: 'SERVER_UE_ENGINE', defaultValue: 'C:\\UE_Source\\UE_5.1-prod')
string(name: 'SERVER_BUILD_FLAGS', defaultValue: '-build -cook -package -stage -archive -pak -allmaps -server -noclient -target=MytaverseServer')
string(name: 'SLACK_CHANNEL', defaultValue: 'ci-cd')
string(name: 'ZIP_PATH', defaultValue: 'windows_pure_web.zip')
```

## Pipeline: GetLatestStable

* Retrieved from `Pipeline: DevGit`

* Unique below

``` groovy
REPO_FOLDER = 'C:\\JenkinsJobs\\Mytaverse_Client\\'
```

## Pipeline: ClientGameCast

* Retirved from `Pipeline Labs` via `Pipeline: DevGit`

``` groovy
choice(name: 'STREAM_CLASS', choices: ['gen4a_ultra', 'gen4a_high', 'gen4n_ultra', 'gen4n_high'])

string(name: 'MYTA_BRANCH', defaultValue: '/Main')
string(name: 'MYTA_CHANGESET', defaultValue: '')
choice(name: 'CLIENT_CONFIG', choices: ['Development', 'Shipping', 'DebugGame'])
string(name: 'CLIENT_BUILD_FLAGS', defaultValue: '-build -cook -package -stage -archive -pak -CrashReporter -target=Mytaverse')
string(name: 'CLIENT_UE_ENGINE', defaultValue: 'C:\\UE_Source\\UE_5.1')
string(name: 'MAPS_CONFIG_NAME', defaultValue: '51Devtest')
choice(name: 'CLIENT_CLEANUP', choices: ['STANDARD', 'MINIMAL', 'FULL', 'DELETE'])
string(name: 'MODEL_NAME', defaultValue: 'Mytaverse51Dev')
string(name: 'GAMECAST_AWS_REGION', defaultValue: 'us-east-2')
string(name: 'EVENT_ID', defaultValue: '6996880367489758')
string(name: 'DESIRED_CAPACITY', defaultValue: '0') //5
```

* Retrieved from DevGit

``` groovy
choice(name: 'TARGET_CONNECTION', choices: ['CC_Dev', 'CC_Prod'])

booleanParam(name: 'UseLinuxClient', defaultValue: false, description: 'Use Linux for client')
```

* Unique parameters

``` groovy
string(name: 'CLIENT_BUILD_DIR_GAMECAST', defaultValue: 'C:\\JenkinsJobs\\Mytaverse_Client_GameCast')
```

## Pipeline: Mytaverse-Client

* Retrived from `Pipeline Labs` via `Pipeline DevGit`

``` groovy
choice(name: 'CLIENT_CONFIG', choices: ['Development', 'Shipping', 'DebugGame'])
string(name: 'MAPS_CONFIG_NAME', defaultValue: '51Devtest')
choice(name: 'CLIENT_CLEANUP', choices: ['STANDARD', 'MINIMAL', 'FULL', 'DELETE'])
```

* Retriieved from  `Pipeline DevGit`

``` groovy
choice(name: 'TARGET_CONNECTION', choices: ['CC_Dev', 'CC_Prod'])
booleanParam(name: 'UseGameCastClient', defaultValue: false, description: 'Use GameCast for client')
```

* Unique parameters

``` groovy
string(name: 'BUILD_DIR', defaultValue: 'C:\\JenkinsJobs\\Mytaverse_Client')
string(name: 'CLIENT_BRANCH', defaultValue: '/Main')
string(name: 'CLIENT_BUILD_FLAGS', defaultValue: '-build -cook -package -stage -archive -pak -CrashReporter -target=Mytaverse')
string(name: 'CLIENT_CHANGESET', defaultValue: '')
choice(name: 'CLIENT_PLATFORM', choices: ['Win64', 'Linux'])
string(name: 'UE_ENGINE', defaultValue: 'C:\\UE_Source\\UE_5.1')
booleanParam(name: 'UseLinuxClient', defaultValue: false, description: 'Use Linux for client')
string(name: 'clientPlasticChangeSet', defaultValue: '')
choice(name: 'NODE_WITH_LABEL', choices: ['project_builder', 'project_builder-fast', 'project_builder-fast-yovko'])
```

## Pipeline: Pure Web

``` groovy
string(name: 'BUILD_DIR', defaultValue: 'C:\\JenkinsJobs\\Mytaverse_Client')
string(name: 'CLIENT_OS', defaultValue: 'windows')
string(name: 'MODEL_NAME', defaultValue: 'Mytaverse51Dev')
string(name: 'PW_CLIENT_ID', defaultValue: 'd2f605c848df776f341e7c24')
string(name: 'PW_CLIENT_SECRET', defaultValue: '3c2bc7fe9a1670ba09e8f3771fa1562c4f304d1a3591f616')  
string(name: 'PW_PATH', defaultValue: 'C:\\Users\\Builder\\AppData\\Roaming\\npm\\pw.cmd')
string(name: 'ZIP_PATH', defaultValue: 'windows_pure_web.zip')
```

## Pipeline: GameLift

* Retrived from `Pipeline: DevGit`

``` groovy
booleanParam(name: 'UseLinuxServer', defaultValue: true, description: 'Use Linux for server')

choice(name: 'SERVER_CLEANUP', choices: ['STANDARD', 'MINIMAL', 'FULL', 'DELETE'])
choice(name: 'SERVER_CONFIG', choices: ['Development', 'Shipping'])
choice(name: 'TARGET_CONNECTION', choices: ['CC_Dev', 'CC_Prod'])
choice(name: 'AWS_CREDS', choices: ['aws-jenkins-dev', 'aws-jenkins-prod'])
choice(name: 'AWS_INSTANCE_TYPE', choices: ['c5.2xlarge', 'c5.xlarge', 'c5.large'])

string(name: 'ALIAS_ID', defaultValue: 'alias-56aeab7d-133f-49d3-929f-057621c33159')
string(name: 'AWS_CONCURRENT_EXECUTIONS', defaultValue: '4')
string(name: 'AWS_REGION', defaultValue: 'us-east-1')
string(name: 'CUSTOM_BUILD_NUMBER', defaultValue: '')
string(name: 'MAPS_CONFIG_NAME', defaultValue: '51Devtest')
string(name: 'MODEL_NAME', defaultValue: 'Mytaverse51Dev')
string(name: 'MYTA_BRANCH', defaultValue: '/Main')
string(name: 'MYTA_CHANGESET', defaultValue: '')
string(name: 'SERVER_BUILD_DIR', defaultValue: 'C:\\JenkinsJobs\\Mytaverse_Server')
string(name: 'SERVER_BUILD_FLAGS', defaultValue: '-build -cook -package -stage -archive -pak -allmaps -server -noclient -target=MytaverseServer')
string(name: 'SERVER_UE_ENGINE', defaultValue: 'C:\\UE_Source\\UE_5.1-prod')
```
