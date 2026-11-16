const {ApolloServer} = require("@apollo/server");
const {startStandaloneServer} = require("@apollo/server/standalone");
const {ApolloGateway, IntrospectAndCompose} = require("@apollo/gateway");

const gateway = new ApolloGateway({
    supergraphSdl: new IntrospectAndCompose({
       subgraphs:[
        {name: 'neobank', url: 'http://localhost:8080/graphql'},
        {name: 'beneficios', url: 'http://localhost:8081/graphql'}
       ]
    })
});

const server= new ApolloServer({ gateway });

startStandaloneServer(server, { listen: { port: 4000 } })
  .then(({ url }) => console.log(`Gateway disponible: ${url}`))
  .catch(error => {
    console.error('No fue posible iniciar el gateway:', error);
    process.exitCode = 1;
});