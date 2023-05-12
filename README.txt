***Projeto desenvolvido por Helder Nicollas e Gabriel Dias***

Diário e Avaliação de Atividades

Pergunta 01: Conseguiu ou não realizar tudo o que foi proposto?
R_ 
Sim.

Resposta para pergunta 2: Quais os possíveis problemas que se identifica no código?
R_ 
O projeto não traz o ID das atividades quando o usuário faz algum fitro das mesmas.
O projeto só consegue trazer o ID das atividades quando o usuário utiliza a função 'Mostrar todas as atividades'.
Com este projeto não é possível editar categoria de atividades.


Resposta para pergunta 3: Sentiu dificuldade para desenvolver o projeto? Quais?
R_
O maior obstáculo para construção desse projeto, para a equipe, foi o manuseio de datas para geração de filtros de atividades.
Para resolução disso, foram utilizadas as classes DateTimeFormatter e LocalDate para possibilitar o manuseio e tratamento de datas com facilidade.

Os filtros de datas foram construídos utilizando o conceito de intervalo entre datas, onde o usuário pode informar uma data inicial e uma data final para filtro de atividades.
Com esse filtro de datas, é possível filtrar por dia, semana, mês e até ano.


Fontes de pesquisa:
Utilização da classe LocalDate: Baeldung, Extracting Year, Month and Day from Date in Java, https://www.baeldung.com/java-year-month-day.
Utilização da classe DateTimeFormatter: StackOverflow, How to format LocalDate object to MM/dd/yyyy and have format persist, https://stackoverflow.com/questions/39689866/how-to-format-localdate-object-to-mm-dd-yyyy-and-have-format-persist.