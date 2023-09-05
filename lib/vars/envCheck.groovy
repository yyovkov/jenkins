def call(body) {
    if (params.CUSTOMER == "") {
        currentBuild.result = 'ABORTED'
        error("CUSTOMER must be set")
    }

    if (params.OWNER == "") {
        currentBuild.result = 'ABORTED'
    error("OWNERmust be set")
    }
}
