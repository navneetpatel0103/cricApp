const authData = [
  { email: 'navneetpatel0103', password: '12345' },
  { email: 'ayushkumar2608', password: '12345' },
];

export function checkCredentials(email, password) {
  return authData.some(user => user.email === email && user.password === password);
}