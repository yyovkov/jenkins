def call(Map conf) {
    // Name: gameliftAliasUpdateFleet.groovy
    // Update aleryd existing GameLift alias with provided leet_arn
    // 
    // Require parameters:
    //  * alias_arn
    //  * instaneManagerUrl

    def req_body = '{ "aliasId":"' + conf.alias_arn + '", "destroySessions": true}'

    def baseUrl = new URL(conf.instaneManagerUrl)

    println 'Request body is: ' + req_body 

}
