const mongoClient = require("mongodb").MongoClient

const databaseUrl = process.env.DATABASE_URL
const databaseName = process.env.DATABASE_NAME

// Database collection
const USER_COLLECTION = "user"
const CHAT_COLLECTION = "chat"
const CHAT_TRANSACTION_COLLECTION = "chat_transaction"

const client = new mongoClient(databaseUrl)

async function connectDatabase() {
    await client.connect()
}

function getDatabase() {
    return client.db(databaseName)
}

function getCollection(name) {
    return getDatabase().collection(name)
}

async function closeConnection() {
    await client.close()
}

module.exports = {
    connectDatabase,
    closeConnection,
    getDatabase,
    getCollection,
    USER_COLLECTION,
    CHAT_COLLECTION,
    CHAT_TRANSACTION_COLLECTION
}

