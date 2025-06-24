# websocket + redis 알림 연습

### 💡 목표   
API를 통해 메시지를 발행하면,
Redis Pub/Sub을 통해 서버가 이를 수신하고,
WebSocket을 통해 프론트(Vue)로 실시간 알림을 전송

### 🧪 전체 동작 흐름   
POST /api/alarm - BODY message(JSON)   
        ↓   
RedisTemplate.convertAndSend("alarm-channel", message)   
        ↓   
RedisSubscriber.onMessage() (Redis Pub/Sub 수신)   
        ↓   
SimpMessagingTemplate.convertAndSend("/sub/alarm", payload)   
        ↓   
Vue WebSocket 클라이언트 구독('/sub/alarm') 후 메시지 수신   
