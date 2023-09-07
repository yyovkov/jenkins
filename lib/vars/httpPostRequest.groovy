// Not finished yet

def requestUrl = GetRequestUrl("https://inm-tour.mytaverselabs.com/event/v2/publishSelfService")
def body = '{"aliasId":"alias-98e5f3e1-b69c-437f-8257-6f98156c01bc", "destroySessions": true}'

println("Request Url: " + requestUrl)
println("Request Body: " + body)

def response = httpRequest httpMode: 'POST',
    ignoreSslErrors: true,
    requestBody: body,
    responseHandle: 'NONE',
    url: requestUrl,
    consoleLogResponseBody: true,
    contentType: 'APPLICATION_JSON',
    wrapAsMultipart: false

println("Status: " + response.status)
println("Content: " + response.content)
