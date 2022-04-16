const failDataBody = {
    data: false,
}

function createDataBody(responseData) {
    return {
        data: responseData,
    }
}

function returnSuccess(res, body) {
    if (body) {
        res.status(200).json(createDataBody(body))
    } else {
        res.status(200).json({})
    }
}

function returnBadRequest(res, body) {
    if (body) {
        res.status(400).json(createDataBody(body))
    } else {
        res.status(400).json(failDataBody)
    }
}

function returnForbidden(res, body) {
    if (body) {
        res.status(403).json(createDataBody(body))
    } else {
        res.status(403).json(failDataBody)
    }
}

function returnNotFound(res, body) {
    if (body) {
        res.status(404).json(createDataBody(body))
    } else {
        res.status(404).json(failDataBody)
    }
}

function returnInternalServer(res, body) {
    if (body) {
        res.status(500).json(createDataBody(body))
    } else {
        res.status(500).json(failDataBody)
    }
}

module.exports = {
    createDataBody,
    returnSuccess,
    returnBadRequest,
    returnForbidden,
    returnNotFound,
    returnInternalServer,
}
