class Link {
    public int iData; // Item de dado
    public Link next; // Próximo nó na lista

    public Link(int id) { // Construtor
        iData = id; // Inicializa os dados
        // ("next" é automaticamente definido como null)
    }

    public void displayLink() { // Exibição dos nós
        System.out.print("[" + iData + "] ");
    }
}

class LinkList {
    private Link first; // Referência ao primeiro nó da lista

    public LinkList() { // Construtor
        first = null; // Sem itens na lista ainda
    }

    public boolean vazio() {
        return (first == null);
    }

    public void inserePrimeiro(int id) { // Insere no início da lista
        Link newLink = new Link(id); // Cria o novo nó
        newLink.next = first; // newLink -> first antigo
        first = newLink; // first -> newLink
    }

    public Link verUltimo() {
        if (vazio()) {
            return null;
        }

        Link current = first;
        while (current.next != null) {
            current = current.next;
        }
        return current;
    }

    public void insereUltimo(int id) { // Insere no final da lista
        Link newLink = new Link(id); // Cria o novo nó

        if (first == null) { // Se a lista estiver vazia
            first = newLink; // O novo nó se torna o primeiro
        } else {
            Link current = first;
            while (current.next != null) {
                current = current.next; // Move para o próximo nó
            }
            current.next = newLink; // Adiciona o novo nó ao final da lista
        }
    }

    public Link encontrar(int chave) {
        Link current = first; // Começa pelo primeiro nó
        while (current != null) { // Enquanto não chegar ao final da lista
            if (current.iData == chave) { // Se encontrar o nó com o valor desejado
                return current; // Retorna o nó encontrado
            }
            current = current.next; // Senão, vai para o próximo nó
        }
        return null; // Retorna null se não encontrar o nó com o valor desejado
    }

    public Link deletar(int chave) {
        Link current = first;
        Link previous = null;

        while (current != null && current.iData != chave) { // Percorre a lista até encontrar o nó com o valor "chave"
            previous = current; // Atualiza o nó anterior para ser o nó atual antes de avançar
            current = current.next; // Avança para o próximo nó
        }

        if (current == null) {
            return null; // Retorna null se o valor não for encontrado na lista
        }

        if (previous == null) {
            first = first.next; // Remove o primeiro nó
        } else {
            previous.next = current.next; // Remove nó no meio ou final
        }

        return current; // Retorna o nó removido
    }

    public Link deletarUltimo() {
        if (vazio()) {
            return null; // Retorna null se a lista estiver vazia
        }

        if (first.next == null) {
            Link temp = first;
            first = null;
            return temp; // Retorna o único nó da lista (último nó)
        }

        Link current = first;
        Link previous = null;

        while (current.next != null) {
            previous = current;
            current = current.next;
        }

        previous.next = null; // Remove a ligação do último nó
        return current; // Retorna o último nó removido
    }

    public void displayList() { // Mostra na tela
        System.out.print("(primeiro -> último): ");
        Link current = first; // Começa no começo da lista
        while (current != null) { // Até o final da lista
            current.displayLink(); // Imprime todos os dados
            current = current.next; // Move para o próximo nó
        }
        System.out.println(" ");
    }
}