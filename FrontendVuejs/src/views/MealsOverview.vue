<template>
  <TheHeader />
  <div class="diet-page">
    <section class="introduction">
      <h1>Dieta Personalizada</h1>
      <p>
        A dieta personalizada é projetada para atender às necessidades específicas do seu corpo com
        base nas métricas que você forneceu. Ao ajustar os macronutrientes e as calorias de acordo
        com seus objetivos (ganho de massa, emagrecimento ou manutenção de peso), essa dieta pode
        melhorar sua saúde geral, aumentar sua energia e otimizar seu desempenho físico.
      </p>
      <p>
        Abaixo, você encontrará sugestões de refeições para diferentes partes do dia. Essas
        sugestões são adaptadas para atender às suas necessidades nutricionais e ajudar a alcançar
        seus objetivos de forma eficaz.
      </p>
    </section>

    <section class="meal-plans">
      <div
        v-for="(meal, index) in meals"
        :key="index"
        class="meal-table"
      >
        <div class="table-header">
          <h2>{{ meal.name }}</h2>
          <p class="description">{{ meal.description }}</p>
        </div>

        <table>
          <thead>
            <tr>
              <th>Alimento</th>
              <th>Quantidade</th>
              <th>Calorias</th>
            </tr>
          </thead>
          <tbody>
            <tr
              v-for="(item, itemIndex) in meal.items"
              :key="itemIndex"
            >
              <td>{{ item.food }}</td>
              <td>{{ item.quantity }}</td>
              <td>{{ item.calories }}</td>
            </tr>
          </tbody>
        </table>

        <div class="edit-buttons">
          <button class="btn-edit">Editar Refeição</button>
        </div>
      </div>

      <div class="edit-full-diet">
        <button class="btn-edit-full">Editar Dieta Completa</button>
      </div>
    </section>
  </div>

  <WhatsAppPopup />
  <ScrollToTopButton />
  <TheFooter />
</template>

<script>
import TheHeader from '@/components/layout/AppHeader.vue'
import TheFooter from '@/components/layout/AppFooter.vue'
import WhatsAppPopup from '@/components/interactions/WhatsAppFloatingButton.vue'
import ScrollToTopButton from '@/components/layout/ScrollToTopButton.vue'

const defaultMeals = [
  {
    name: 'Café da Manhã',
    description: 'Inicie seu dia com uma refeição equilibrada para fornecer energia e nutrientes essenciais.',
    items: [
      { food: 'Ovos', quantity: '2 unidades', calories: 140 },
      { food: 'Pão Integral', quantity: '1 fatia', calories: 80 },
      { food: 'Fruta', quantity: '1 unidade', calories: 60 }
    ]
  },
  {
    name: 'Almoço',
    description: 'Uma refeição nutritiva para manter a energia durante o dia, com proteínas magras e vegetais.',
    items: [
      { food: 'Peito de Frango', quantity: '150g', calories: 165 },
      { food: 'Arroz Integral', quantity: '1 xícara', calories: 215 },
      { food: 'Brócolis', quantity: '1 xícara', calories: 55 }
    ]
  },
  {
    name: 'Lanche da Tarde',
    description: 'Uma pequena refeição para manter o metabolismo ativo e controlar a fome.',
    items: [
      { food: 'Iogurte Natural', quantity: '1 pote', calories: 100 },
      { food: 'Amêndoas', quantity: '10 unidades', calories: 70 }
    ]
  },
  {
    name: 'Jantar',
    description: 'Uma refeição leve e nutritiva para finalizar o dia, promovendo a recuperação e o relaxamento.',
    items: [
      { food: 'Salmão', quantity: '150g', calories: 200 },
      { food: 'Quinoa', quantity: '1 xícara', calories: 220 },
      { food: 'Espinafre', quantity: '1 xícara', calories: 40 }
    ]
  },
  {
    name: 'Ceia',
    description: 'Uma pequena refeição antes de dormir para ajudar na recuperação muscular e melhorar o sono.',
    items: [
      { food: 'Chá Verde', quantity: '1 xícara', calories: 0 },
      { food: 'Queijo Cottage', quantity: '100g', calories: 80 }
    ]
  }
]

export default {
  name: 'MealsPage',
  components: {
    TheHeader,
    TheFooter,
    WhatsAppPopup,
    ScrollToTopButton
  },
  data() {
    return {
      meals: defaultMeals
    }
  }
}
</script>

<style scoped>
.diet-page {
  font-family: Arial, sans-serif;
  padding: 40px 20px;
  max-width: 1200px;
  margin: 0 auto;
}

.introduction {
  margin-bottom: 40px;
}

.introduction h1 {
  font-size: 32px;
  color: #162938;
  margin-bottom: 15px;
}

.introduction p {
  font-size: 16px;
  line-height: 1.6;
  color: #444;
}

.meal-plans {
  margin-top: 30px;
}

.meal-table {
  margin-bottom: 40px;
  border-radius: 10px;
  overflow: hidden;
  box-shadow: 0 6px 16px rgba(0, 0, 0, 0.15);
  background-color: #fff;
}

.table-header {
  background-color: #f0f4f8;
  padding: 20px;
}

.table-header h2 {
  font-size: 22px;
  margin: 0;
  color: #162938;
}

.description {
  font-size: 14px;
  color: #666;
  margin-top: 5px;
}

table {
  width: 100%;
  border-collapse: collapse;
}

table th,
table td {
  border: 1px solid #e0e0e0;
  padding: 14px;
  text-align: left;
  font-size: 15px;
}

table th {
  background-color: #e9f0f4;
  color: #333;
  font-weight: bold;
}

table tbody tr:nth-child(odd) {
  background-color: #fafafa;
}

table tbody tr:nth-child(even) {
  background-color: #fff;
}

.edit-buttons {
  text-align: right;
  padding: 15px 20px;
  background-color: #f9f9f9;
}

.edit-full-diet {
  text-align: center;
  margin-top: 40px;
}

.btn-edit,
.btn-edit-full {
  background-color: #162938;
  color: #fff;
  border: none;
  border-radius: 6px;
  padding: 10px 24px;
  cursor: pointer;
  font-size: 15px;
  font-weight: 600;
  transition: 0.3s ease;
}

.btn-edit:hover,
.btn-edit-full:hover {
  background-color: #0e1f26;
  transform: scale(1.05);
}
</style>
