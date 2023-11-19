import ws from "ws";
const { Server } = ws;
const clients = {};

const wss = new Server({port: 8080});

wss.on("connection", (ws))