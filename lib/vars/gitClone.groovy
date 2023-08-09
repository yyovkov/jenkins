def call(Map config) {
    // Example pipeline invokation
    //  plasticClone(
    //      git_url: "git@bitbucket.org:gathr1/tests_tagupdate.git",
    //      git_branch: "master",
    //      git_credentials: "ssh-bitbucket-infra-ro"
    //  )
    git changelog: false, poll: false, url: "${config.git_url}", branch: 'master', credentialsId: "${config.git_credentials}"
}
