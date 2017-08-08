const secrets = require('../config/secrets');

const applicationName = 'Prescritor QR-CODE';
const senderAddress = 'Mailing <mailing@starter.com>';
const service = {};
const mailer = require('sendgrid')(secrets.sendgrid.api_key);

service.sendRequestPasswordEmail = (email, host, token, done) => {
  const mailOptions = {
    to: email,
    from: senderAddress,
    subject: `Reset your password on ${applicationName}`,
    text: `You are receiving this email because you have requested the reset of the password for your account.
    Please click on the following link, or paste this into your browser to complete the process:
    http://${host}/reset/${token}
    If you did not request this, please ignore this email and your password will remain unchanged`,
  };

  mailer.send(mailOptions, done);
};

service.sendPasswordChangeNotificationEmail = (email, done) => {
  const mailOptions = {
    to: email,
    from: senderAddress,
    subject: `Your ${applicationName} password has been changed`,
    text: `Hello,
    This is a confirmation that the password for your account ${email} has just been changed.`
  };

  mailer.send(mailOptions, done);
};

module.exports = service;
