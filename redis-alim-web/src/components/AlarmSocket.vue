<script setup lang="ts">
import { onMounted, ref } from 'vue'
import SockJS from 'sockjs-client';
import {Client} from "@stomp/stompjs";

const messages = ref<string[]>([])

onMounted(() => {
  const socket = new SockJS('http://localhost:8080/ws')
  const stompClient = new Client({
    webSocketFactory: () => socket,
    debug: console.log,
    reconnectDelay: 5000,
  })

  stompClient.onConnect = () => {
    stompClient.subscribe('/sub/alarm', (message) => {
      messages.value.push(message.body)
    })
  }

  stompClient.activate()
})
</script>

<template>
  <div>
    <h2>🔔 실시간 알림</h2>
    <ul>
      <li v-for="(msg, index) in messages" :key="index">{{ msg }}</li>
    </ul>
  </div>
</template>
