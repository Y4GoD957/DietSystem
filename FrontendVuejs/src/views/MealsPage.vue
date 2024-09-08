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
        <div v-for="(meal, index) in meals" :key="index" class="meal-table">
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
              <tr v-for="(item, itemIndex) in meal.items" :key="itemIndex">
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
  import TheHeader from '@/components/TheHeader.vue'
  import TheFooter from '@/components/TheFooter.vue'
  import WhatsAppPopup from '@/components/WhatsAppPopup.vue'
  import ScrollToTopButton from '@/components/ScrollToTopButton.vue'
  
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
        meals: [
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
      }
    }
}
  </script>
  
  <style scoped>
.diet-page {
  font-family: Arial, sans-serif;
  padding: 20px;
}

.introduction {
  margin-bottom: 30px;
}

.introduction h1 {
  font-size: 24px;
  margin-bottom: 10px;
}

.introduction p {
  font-size: 16px;
  line-height: 1.5;
}

.meal-plans {
  margin-top: 20px;
}

.meal-table {
  margin-bottom: 30px;
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  background-color: #fff;
}

.table-header {
  background-color: #f4f4f4;
  padding: 15px;
}

.table-header h2 {
  font-size: 20px;
  margin: 0;
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
  border: 1px solid #ddd;
  padding: 12px;
  text-align: left;
}

table th {
  background-color: #e0e0e0;
  color: #333;
}

table td {
  width: 33.33%; /* Garantir que todas as colunas tenham a mesma largura */
}

table tbody tr:nth-child(odd) {
  background-color: #f9f9f9;
}

table tbody tr:nth-child(even) {
  background-color: #ffffff;
}

.edit-buttons {
  text-align: center;
  padding: 10px;
  background-color: #f9f9f9;
}

.btn-edit,
.btn-edit-full {
  background-color: #162938;
  color: #fff;
  border: none;
  border-radius: 5px;
  padding: 10px 20px;
  cursor: pointer;
  font-size: 16px;
  font-weight: bold;
  transition: background-color 0.3s ease, transform 0.3s ease;
  display: inline-block;
}

.btn-edit:hover,
.btn-edit-full:hover {
  background-color: #0e1f26;
  transform: scale(1.05);
}

.edit-full-diet {
  text-align: center;
  margin-top: 20px;
}
</style>