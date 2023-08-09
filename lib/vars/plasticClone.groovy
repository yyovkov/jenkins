def call(Map config) {
    echo """
        Running checkout of PlasticSCM repositry:
        cm(
            branch: "${config.branch}",
            changelog: ${config.changelog},
            cleanup: "${config.cleanup}",
            changeset: "${config.changeset}",
            credentialsId: "${config.credentialsId}",
            poll: ${config.poll},
            repository: "${config.repository}",
            server: "${config.server}",
            workingMode: "${config.workingMode}",
            directory: "${config.directory}"
        )
    """

    def plasticVars = cm(
        branch: "${config.branch}",
        changelog: "${config.changelog}",
        cleanup: "${config.cleanup}",
        changeset: "${config.changeset}",
        credentialsId: "${config.credentialsId}",
        poll: "${config.poll}",
        repository: "${config.repository}",
        server: "${config.server}",
        workingMode: "${config.workingMode}",
        directory: "${config.directory}"
    )

    return plasticVars;
}
