
const protocol = 'http';
const server = '192.168.56.104';
const port = '8080';
const webServiceContext = 'restWs';

export const environment = {
  production: true,

  authenticationUrl: protocol + '://' + server + ':' + port + '/' + webServiceContext + '/auth/login'
};
