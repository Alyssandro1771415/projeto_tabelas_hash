import os
import pandas as pd
import matplotlib.pyplot as plt

file_path = "./hash_table_results.csv"

# Carregar os dados do CSV
df = pd.read_csv(file_path)

# Verificar se o DataFrame foi carregado corretamente
if df.empty:
    raise ValueError(f"O arquivo CSV em {file_path} está vazio ou não pôde ser carregado corretamente.")

# Separar os dados por Dataset
df_100000 = df[df['Dataset'] == '100000 Unique']
df_300000 = df[df['Dataset'] == '300000 Repeated']

# Função para criar gráficos separados
def plot_individual_graph(df, dataset_label, metric, ylabel, color):
    plt.figure(figsize=(10, 6))
    plt.bar(df['HashTable'], df[metric], color=color)
    plt.title(f'{ylabel} ({dataset_label})')
    plt.xlabel('HashTable')
    plt.ylabel(ylabel)
    plt.xticks(rotation=45, ha="right")
    plt.tight_layout()
    plt.show()

# Função para chamar a criação dos gráficos
def plot_graphs(df, dataset_label):
    plot_individual_graph(df, dataset_label, 'InsertTime', 'Tempo de Inserção (ms)', 'blue')
    plot_individual_graph(df, dataset_label, 'SearchTime', 'Tempo de Busca (ms)', 'green')
    plot_individual_graph(df, dataset_label, 'Collisions', 'Quantidade de Colisões', 'red')
    plot_individual_graph(df, dataset_label, 'Rehashes', 'Quantidade de Rehashes', 'purple')

# Gerar gráficos para 100000 Unique
plot_graphs(df_100000, "100000 Unique")

# Gerar gráficos para 300000 Repeated
plot_graphs(df_300000, "300000 Repeated")
