import AjaxForm from './ajax-form'

export default function ResetPasswordForm() {
	new AjaxForm({
	    selector: '.js-reset-password-form',
	    endpoint: '/reset-password'
	});
}