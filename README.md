# README.md

This project contains jenkins code, used to manager UE5 builds and deployments

## Setup Library with Bitbucket branch

Navigate to: `Manage Jenkins` --> `System` --> `Global Pipeline Libraries` and
fill bellow fields

* **Library Name**: `mytaverse`
* **Default Version**: `my-development-branch` # repo branch name if not master
* **Retrieval method**: `Legacy SCM`
* **Branch Specifier**: `my-development-branch` # repo branch name if not master
* **Library Path**: `lib` # subfolder in which `vars` directory is located

## Create Jenkins Folder

* Setup variables

``` bash
JENKINS_USER=admin
JENKINS_PASSWORD=admin
JENKINS_HOST="https://jenkins.lab.yyovkov.net"
curl -sL -o /tmp/jenkins-cli.jar ${JENKINS_HOST}/jnlpJars/jenkins-cli.jar
alias jenkins-cli="java -jar /tmp/jenkins-cli.jar -s ${JENKINS_HOST} -auth ${JENKINS_USER}:${JENKINS_PASSWORD}"
```

* Create Folder

``` bash
export FOLDER_NAME="mytaverse"
export FOLDER_SLOGAN="Mytaverse"
export FOLDER_DESCRIPTION="Mytaverse Pipelines to build and deploy UE5"
jenkins-cli create-job ${FOLDER_NAME} <<< $(envsubst < templates/pipeline/folder.xml)
```

## Create Jenkins Pipeline

### Setup variables

``` bash
export JOB_FOLDER="mytaverse"
export JOB_NAME=build_and_deploy
export JOB_SLOGAN="Build and Deploy UE5"
export JOB_DESCRIPTION="Build and Deploy UE5"
export GIT_REPO="git@github.com:yyovkov/jenkins.git"
export GIT_BRANCH="*/develop"
export GIT_CREDENTIALS="gh-deploy-key"
export PATH_JENKINSFILE="pipelines/build_and_deploy/Jenkinsfile"
```

Where:

* **JOB_DESCRIPTION** - desciption of job visible in Jenkins
* **JOB_SLOGAN** - the name of the jenkins build, as it will be visible
* **JOB_NAME** - Name of the Jenkins job
* **GIT_REPO** - repository from where the Jenkinsfile will be fetch
* **GIT_BRANCH** - branch storing the Jenkins file
* **GIT_CREDENTIALS** - Jenkins credentials id used to access the repo
* **PATH_JENKINSFILE** - relative path of the Jenkins file in the repo
* **JOB_FOLDER** - Jenkins folder in which the pipeline will be placed

### Create the pipeline

``` bash
jenkins-cli create-job ${JOB_FOLDER}/${JOB_NAME} <<< $(envsubst < templates/pipeline/job_git.xml)
```

## Add Jenkins Credentials

AWS Credentials for GameLift deploy in Sandbox

* Setup variables

``` bash
export CREDS_ID=aws-gamelift-sand
export CREDS_DESCRIPTION="AWS Credential used for GameLift Deployment in Sandbox"
export AWS_ACCESS_KEY_ID=...
export AWS_SECRET_ACCESS_KEY=...
```

* Create the credentials

``` bash
jenkins-cli create-credentials-by-xml system::system::jenkins _ <<< $(envsubst < templates/secrets/aws.xml)
```

## Notes (Delme when finish)

[https://forum.plasticscm.com/topic/22937-latest-changeset-in-branch/]

PlasticProcessingHelper cmHelper = new(cmPath, workingDirectory);

const string latestPrefix = "latest";
const string latestStable = "latest-stable";

mainLabelPart = $"seller-{sellerName}";

var pattern = $"{latestPrefix}-{mainLabelPart}";
var latestStableId = await cmHelper.GetLatestByPattern(pattern);
var latestStableId = await cmHelper.GetLatestByPattern(pattern);

Console.WriteLine($"Exit with {latestStableId}");
return int.Parse(latestStableId);

* Resultint code

``` bash
var pattern = $"latest-{mainLabelPart}";
```

## Notes on Mytaverse-Server pipeline

* Read maps file (.\Devops\Maps\HRWorld.conf) # TODO: Check from where the file is coming

    ``` bash
    # Get Full Path: K:\JenkinsJobs\Mytaverse_Server_5_21\Devops\Maps\HRWorld.conf
    # On my machine: D:\Projects\Mytaverse\PlasticSCM\Devops\Maps\HRWorld.conf
    ```

* Print lines
* Surround mapsLines in `+MapsToCook=(FilePath=...)`

* Read  defaultGamesInit file (.\Config\DefaultGame.ini) # TODO: Check from where the files is coming
* Check lines containgin (MapsToCook=(FilePath="))

## Variables reverse-envineered

### Jaime's machine

* **SERVER_BUILD_DIR** - `K:\JenkinsJobs\Mytaverse_Server_5_21`

### My Machine

* **SERVER_BUILD_DIR** - `D:\Projects\Mytaverse\PlasticSCM`

## Credentials Required

* **ssh-bitbucket-infra-ro** - SSH Key for reading bitbucket repositories
* **CI_CD_SERVER** - Personal usename/password of Viktar. Viktor to explain
