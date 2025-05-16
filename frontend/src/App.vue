<script setup>
import HelloWorld from './components/HelloWorld.vue';
</script>

<template>
  <nav>
    <router-link to="/products">Produtos</router-link> |
    <router-link to="/create-product">Criar Produto</router-link> |
    <router-link to="/orders">Pedidos</router-link> |
    <router-link to="/login">Sair</router-link>
  </nav>
  <router-view />
</template>

<style scoped>
.logo {
  height: 6em;
  padding: 1.5em;
  will-change: filter;
  transition: filter 300ms;
}
.logo:hover {
  filter: drop-shadow(0 0 2em #646cffaa);
}
.logo.vue:hover {
  filter: drop-shadow(0 0 2em #42b883aa);
}
</style>
