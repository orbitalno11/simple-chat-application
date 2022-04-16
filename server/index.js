require("dotenv").config()

const express = require("express")
const http = require("http")
const { Server } = require("socket.io")

const {
    connectDatabase,
    getCollection,
    closeConnection,
    CHAT_COLLECTION,
    USER_COLLECTION,
    CHAT_TRANSACTION_COLLECTION,
} = require("./mongodb-utils")
const {
    returnSuccess,
    returnForbidden,
    returnInternalServer,
} = require("./server-handler")

const app = express()
const server = http.createServer(app)
const io = new Server(server)

const serverPort = process.env.SERVER_PORT || 5000

// Socket

io.on("connection", (socket) => {
    console.log("user connected")
})

// API
app.use(express.json())
app.use(express.urlencoded())

app.get("/", (req, res) => {
    res.send("CHAT APP")
})

app.get("/v1/chats", async (req, res) => {
    try {
        const { user: userId } = req.query

        connectDatabase()

        const chats = (
            await getCollection(CHAT_COLLECTION).findOne({
                user_id: userId,
            })
        ).chats

        const result = []
        for (let index = 0; index < chats.length; index++) {
            const chat = chats[index]
            const { participant_id: participantId } = chat

            const participant = await getCollection(USER_COLLECTION).findOne({
                user_id: participantId,
            })

            result.push({
                name: participant.name,
                picture: participant.picture_url,
                ...chat,
            })
        }

        returnSuccess(res, result)
    } catch (e) {
        console.error(e)
        returnInternalServer(res)
    } finally {
        closeConnection()
    }
})

app.get("/v1/chats/:chat_id", async (req, res) => {
    try {
        const { user: userId } = req.query
        const { chat_id: chatId } = req.params

        connectDatabase()

        const chat = await getCollection(CHAT_TRANSACTION_COLLECTION).findOne({
            transaction_id: chatId,
        })

        if (!chat.participant.includes(userId)) {
            returnForbidden(res, {
                data: "No permission",
            })
        }

        const participantId = chat.participant.filter(
            (user) => user !== userId
        )[0]
        const result = {
            participant: {
                name: mockedData.user[participantId].name,
            },
            messages: chat.transaction.sort(
                (a, b) => a.send_time - b.send_time
            ),
        }

        returnSuccess(res, result)
    } catch (e) {
        console.error(e)
        returnInternalServer(res)
    } finally {
        closeConnection()
    }
})

app.post("/v1/chats/:chat_id", async (req, res) => {
    try {
        const { user: userId } = req.query
        const { chat_id: chatId } = req.params
        const body = req.body

        connectDatabase()

        const chat = await getCollection(CHAT_TRANSACTION_COLLECTION).findOne({
            transaction_id: chatId,
        })

        if (!chat.participant.includes(userId)) {
            returnForbidden(res, {
                data: "No permission",
            })
        }

        await getCollection(CHAT_TRANSACTION_COLLECTION).updateOne(
            { transaction_id: chatId },
            {
                $push: {
                    transaction: {
                        sender_id: userId,
                        message: body.message,
                        send_time: body.send_time,
                    },
                },
            }
        )

        returnSuccess(res)
    } catch (e) {
        console.error(e)
        returnInternalServer(res)
    } finally {
        closeConnection()
    }
})

server.listen(serverPort, () => {
    console.log(`CHAT APP SERVER WAS STARTED ON PORT ${serverPort}`)
})
