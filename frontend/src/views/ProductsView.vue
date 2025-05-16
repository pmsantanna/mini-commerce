<template>
  <div>
    <h2>Produtos</h2>
    <ul>
      <li v-for="p in products" :key="p.id">
        {{ p.name }} - R$ {{ p.price.toFixed(2) }}
      </li>
    </ul>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import api from '../services/api';

const products = ref([]);

onMounted(async () => {
  const res = await api.get('/products');
  products.value = res.data;
});
</script>
