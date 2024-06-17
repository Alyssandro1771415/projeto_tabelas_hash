import os
import pandas as pd
import matplotlib.pyplot as plt

file_path = "./hash_table_results.csv"

df = pd.read_csv(file_path)

if df.empty:
    raise ValueError(f"O arquivo CSV em {file_path} está vazio ou não pôde ser carregado corretamente.")

df_100000 = df[df['Dataset'] == '100000 Unique']
df_300000 = df[df['Dataset'] == '300000 Repeated']

output_dir = "Graphs"
if not os.path.exists(output_dir):
    os.makedirs(output_dir)

def plot_individual_graph(df, dataset_label, metric, ylabel, color):
    plt.figure(figsize=(10, 6))
    plt.bar(df['HashTable'], df[metric], color=color)
    plt.title(f'{ylabel} ({dataset_label})')
    plt.xlabel('HashTable')
    plt.ylabel(ylabel)
    plt.xticks(rotation=45, ha="right")
    plt.tight_layout()
    plt.savefig(os.path.join(output_dir, f'{metric}_{dataset_label}.png'))
    plt.close()

def plot_graphs(df, dataset_label):
    plot_individual_graph(df, dataset_label, 'InsertTime', 'Tempo de Inserção (ms)', 'blue')
    plot_individual_graph(df, dataset_label, 'SearchTime', 'Tempo de Busca (ms)', 'green')
    plot_individual_graph(df, dataset_label, 'Collisions', 'Quantidade de Colisões', 'red')
    plot_individual_graph(df, dataset_label, 'Rehashes', 'Quantidade de Rehashes', 'purple')

plot_graphs(df_100000, "100000 Unique")

plot_graphs(df_300000, "300000 Repeated")
