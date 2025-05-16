<template>
  <div>
    <h2>📦 Fazer Pedido</h2>

    <div v-if="products.length">
      <div v-for="p in products" :key="p.id">
        <label>
          <input type="checkbox" :value="p.id" v-model="selected" />
          {{ p.name }} – R$ {{ p.price.toFixed(2) }}
        </label>
      </div>
      <button @click="submitOrder">Finalizar Pedido</button>
    </div>

    <h3 style="margin-top: 2rem">📋 Pedidos Anteriores</h3>
    <div v-for="order in orders" :key="order.id" class="order-card">
      <p>
        <strong>Pedido #{{ order.id }}</strong> – {{ order.date }}
      </p>
      <ul>
        <li v-for="item in order.products" :key="item.name">
          {{ item.name }} – R$ {{ item.price.toFixed(2) }}
        </li>
      </ul>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import api from '../services/api';

const products = ref([]);
const selected = ref([]);
const orders = ref([]);

onMounted(async () => {
  await fetchProducts();
  await fetchOrders();
});

async function fetchProducts() {
  const res = await api.get('/products');
  products.value = res.data;
}

async function fetchOrders() {
  const res = await api.get('/orders');
  console.log(res);
  orders.value = res.data;
}

async function submitOrder() {
  if (!selected.value.length) return alert('Selecione ao menos um produto.');

  const res = await api.post('/orders', {
    products: selected.value.map((id) => ({ id })),
  });

  selected.value = [];
  await fetchOrders();
}
</script>

<style scoped>
.order-card {
  border: 1px solid #ddd;
  border-radius: 4px;
  padding: 12px;
  margin-bottom: 16px;
  background-color: #f9f9f9;
}
</style>
