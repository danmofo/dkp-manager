import AjaxForm from './ajax-form'

export default function EditGuildForm() {
	new AjaxForm({
		endpoint: '/guild-management/edit',
		selector: '.js-edit-guild-form'
	});
}