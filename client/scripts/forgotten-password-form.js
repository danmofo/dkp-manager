import AjaxForm from './ajax-form'

export default function ForgottenPasswordForm() {
	new AjaxForm({
		selector: '.js-forgotten-password-form',
		endpoint: '/forgotten-password'
	});
}