<template>
  <div class="whatsapp-popup" @click="redirectToWhatsapp">
    <img src="@/assets/whatsapp.png" alt="WhatsApp Logo" />
  </div>
</template>

<script>
export default {
  name: 'WhatsAppPopup',
  methods: {
    redirectToWhatsapp() {
      // Substitua pelo seu número do WhatsApp ou link
      window.open('https://wa.me/5561981905006', '_blank')
    }
  }
}
</script>

<style scoped>
.whatsapp-popup {
  position: fixed;
  bottom: 20px;
  left: 20px;
  width: 50px;
  height: 50px;
  background-color: #25d366;
  border-radius: 50%;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
  z-index: 1000;
  transition: transform 0.3s;
}

.whatsapp-popup:hover {
  transform: scale(1.1);
}

.whatsapp-popup img {
  width: 70%;
  height: 70%;
}
</style>
