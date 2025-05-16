import { createRouter, createWebHistory } from 'vue-router';
import LoginView from '../views/LoginView.vue';
import ProductsView from '../views/ProductsView.vue';
import CreateProductView from '../views/CreateProductView.vue';
import OrderView from '../views/OrderView.vue';

const routes = [
  { path: '/', redirect: '/login' },
  { path: '/login', component: LoginView },
  { path: '/products', component: ProductsView },
  { path: '/create-product', component: CreateProductView },
  { path: '/orders', component: OrderView },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
