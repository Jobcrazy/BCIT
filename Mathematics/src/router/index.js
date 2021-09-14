import Vue from 'vue';
import Router from 'vue-router';
import Home from '@/components/Home';
import Convert from '@/components/Convert';
import Complement from '@/components/Complement';
import Float from '@/components/Float';
import Division from '@/components/Division';

Vue.use(Router);

export default new Router({
  //mode: 'history',
  //base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      redirect: '/complement',
      name: 'Home',
      component: Home,
      children: [
        {
          name: 'Convert',
          path: '/convert',
          component: Convert,
        },
        {
          name: 'Complement',
          path: '/complement',
          component: Complement,
        },
        {
          name: 'Float',
          path: '/float',
          component: Float,
        },
        {
          name: 'Division',
          path: '/division',
          component: Division,
        },
      ],
    },
  ],
});