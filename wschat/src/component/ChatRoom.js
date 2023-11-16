import { useEffect, useState } from "react";
import { Client } from "@stomp/stompjs";




// const ws = new WebSocket("ws://localhost:3000/ws");
// const stompClient = over(ws);
const ChatRoom = () => {

    let client = null;

    const [chatId, setChatId] = useState("")

    useEffect(() => {
        // Create a WebSocket connection

        client = new Client();

        // Configure the WebSocket endpoint URL
        const websocketUrl = 'ws://localhost:3000/ws'; // Replace with your WebSocket endpoint URL

        // Connect to the WebSocket server
        client.configure({
            brokerURL: websocketUrl,
            debug: function (str) {
                console.log(str);
            },
            onConnect: () => {
                // Perform actions after successful connection
                const destination = `/topic/taskChat/${chatId}`; // Specify the destination for the server-side message handler
                client && client.subscribe(destination, (message) => {
                    console.log('Received message:', JSON.parse(message.body));
                    // Process the received message as needed
                });
            },
            // You can add more event handlers and configuration options as needed
        });

        // Connect to the WebSocket server
        client.activate();


        // Clean up the connection on component unmount
        return () => {
            client && client.deactivate();
        };
    }, [chatId]);



    // const [userData, setUserData] = useState({
    //     username:"",
    //     receiver:"",
    //     connected: false,
    //     message:"",
    // })

    // const handleUserName = (event) => {
    //     const {value} = event.target;
    //     setUserData({...userData, "username":value})
    // }


    // const wsConnect = () => {

    //     stompClient.connect({},
    //         () => {stompClient.subscribe("/taskChat/1", (msg) => console.log(msg))},
    //         (error) => console.log(error)
    //     );
    //     console.log("AAAA")
    //     console.log(stompClient);
    // }

    // const onConnected = () => {
    //     setUserData({...userData, "connected":true});
    // }

    // const registerUser = () => {

    //     stompClient.send("app/taskChat/1",{},document.getElementById("user-name").value)
    // }

    // ws.onmessage = (msg) => console.log(msg);
    // ws.onopen = () => console.log("ready");

    // const send = (e) => {
    //     e.preventDefault();
    //     console.log(document.getElementById("user-name").value);
    //     ws.send(document.getElementById("user-name").value);
    //     console.log(ws);
    //     console.log(ws.readyState);
    //     // console.log(document.getElementById("user-name").value);
    //     // stompClient.send(JSON.stringify(document.getElementById("user-name").value));
    //     // return false;
    // }

    // return (
    //     <div className="container">


    //         <div className="register">
    //             <input id="user-name" placeholder="enter username" />
    //             {/* <button type="button" onClick={wsConnect}>connect</button> */}
    //             <button type="button" onClick={send}>send</button>
    //         </div>

    //     </div>
    // )
}

export default ChatRoom
