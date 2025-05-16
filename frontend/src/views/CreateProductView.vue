<template>
  <div>
    <h2>Criar Produto</h2>

    <input v-model="name" placeholder="Nome" />
    <textarea v-model="description" placeholder="Descrição"></textarea>
    <input v-model.number="price" type="number" placeholder="Preço" />

    <select v-model="categoryId">
      <option disabled value="">Selecione a categoria</option>
      <option v-for="cat in categories" :value="cat.id" :key="cat.id">
        {{ cat.name }}
      </option>
    </select>

    <button @click="createProduct">Salvar</button>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import api from '../services/api';
import { useRouter } from 'vue-router';

const name = ref('');
const description = ref('');
const price = ref(0);
const categoryId = ref('');
const categories = ref([]);
const router = useRouter();

onMounted(async () => {
  const res = await api.get('/categories');
  categories.value = res.data;
});

const createProduct = async () => {
  try {
    await api.post('/products', {
      name: name.value,
      description: description.value,
      price: price.value,
      imageUrl: '',
      categoryId: categoryId.value,
    });
    alert('Produto criado com sucesso!');
    router.push('/products');
  } catch (err) {
    alert('Erro ao criar produto. Verifique se está logado.');
  }
};
</script>
