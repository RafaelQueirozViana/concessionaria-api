# DECISOES.md

Anotações sobre pontos do case que não estavam 100% claros e como resolvi cada um.

**Cor do carro**
Não dá pra saber se é uma lista fixa de cores ou não, então deixei como texto livre em vez de enum. Como a concessionária mexe com marcas diferentes, cada montadora chama a cor de um jeito, e um enum ia quebrar toda hora que entrasse um carro de marca nova.

**Placa**
O case fala que carro 0km às vezes chega sem placa, então esse campo não pode ser obrigatório, mas ainda precisa ser único quando existe, já que duas placas iguais não fazem sentido. No banco isso funciona normal, em que uma constraint unique aceita vários nulos, só não aceita valores repetidos.

**Chassi**
Diferente da placa, todo carro tem chassi desde o início, então esse é obrigatório e único.

**Ano de fabricação e ano do modelo**
O case dá a dica de que são coisas diferentes e o cliente pergunta bastante sobre isso, então virou dois campos separados Integer. Pensei em usar LocalDate, mas não faz sentido, porque não tem dia nem mês, é só o ano-modelo que a indústria automotiva usa, então um Integer resolve.

**Preço**
Usei BigDecimal em vez de double. Double dá problema de arredondamento com dinheiro, como você tinha explicado, em que isso faz total diferença para quebrar todo o sistema e dar prejuizo.

**Quilometragem**
Integer. Não faz sentido km fracionado, e carro novo começa em 0.

**Tipo (novo/seminovo)**
Aqui sim dá pra usar enum, porque o case define só essas duas opções, diferente da cor que é livre.

**Status (disponível/reservado/vendido)**
Esse campo é basicamente a resposta pro problema que a concessionária relatou (dois vendedores negociando o mesmo Corolla). Modelei como enum com essas três opções. A regra de "carro reservado não pode ser negociado de novo" ainda não tá implementada no código, isso fica pra uma próxima etapa, por enquanto o campo existe pra dar suporte a isso.

**CPF**
String, não número. CPF pode começar com zero e nunca é usado em conta matemática, então guardar como Long ia causar problema.

**Telefone**
Mesmo raciocínio do CPF: String, pra não perder formatação.

**CPF Unico, e-mail/telefone não**
O case só fala que CPF não repete. Não coloquei unique em e-mail nem telefone porque isso não foi pedido, e forçar essa regra sem necessidade pode até ser um problema (duas pessoas da mesma casa podem usar o mesmo telefone, por exemplo).

**Relação entre cliente e carro**
Pensei bastante nisso: o case menciona reserva ("cliente deu sinal"), mas em nenhum momento pede pra guardar qual cliente reservou qual carro, nem detalhes tipo data ou valor do sinal. Por enquanto deixei Carro e Cliente como entidades separadas, sem ligação entre elas. O status do carro já resolve o problema central do case. Se uma próxima entrega pedir esse rastreamento, aí sim modelo uma entidade de reserva/venda.

**Vendedor**
O texto cita vendedores no contexto do problema, mas não pede pra registrar nada sobre eles, então não criei essa entidade.