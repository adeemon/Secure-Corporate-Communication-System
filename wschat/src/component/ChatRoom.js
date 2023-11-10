import React, { useState } from "react"
import SockJS from "sockjs-client";
import { over } from "stompjs";

let stompClient = null;

const ChatRoom = () => {

    let sock = new SockJS("http://localhost:8080/ws");
    stompClient = over(sock);

    const [userData, setUserData] = useState({
        username:"",
        receiver:"",
        connected: false,
        message:"",
    })

    return (
        <div>
            {/* {userData.connected?} */}
        </div>
    )
}

export default ChatRoom
