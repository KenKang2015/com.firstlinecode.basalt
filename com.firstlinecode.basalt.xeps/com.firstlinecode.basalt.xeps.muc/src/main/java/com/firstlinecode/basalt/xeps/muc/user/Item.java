package com.firstlinecode.basalt.xeps.muc.user;

import com.firstlinecode.basalt.protocol.core.JabberId;
import com.firstlinecode.basalt.protocol.oxm.convention.annotations.BindTo;
import com.firstlinecode.basalt.protocol.oxm.convention.annotations.TextOnly;
import com.firstlinecode.basalt.protocol.oxm.convention.conversion.annotations.String2Enum;
import com.firstlinecode.basalt.protocol.oxm.convention.conversion.annotations.String2JabberId;
import com.firstlinecode.basalt.xeps.muc.Affiliation;
import com.firstlinecode.basalt.xeps.muc.Role;

public class Item {
	private Actor actor;
	@BindTo("continue")
	private Continue continuee;
	@TextOnly
	private String reason;
	@String2Enum(type=Affiliation.class)
	private Affiliation affiliation;
	@String2JabberId
	private JabberId jid;
	private String nick;
	@String2Enum(type=Role.class)
	private Role role;
	
	public Actor getActor() {
		return actor;
	}
	
	public void setActor(Actor actor) {
		this.actor = actor;
	}
	
	public Continue getContinuee() {
		return continuee;
	}
	
	public void setContinuee(Continue continuee) {
		this.continuee = continuee;
	}
	
	public String getReason() {
		return reason;
	}
	
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public Affiliation getAffiliation() {
		return affiliation;
	}
	
	public void setAffiliation(Affiliation affiliation) {
		this.affiliation = affiliation;
	}
	
	public JabberId getJid() {
		return jid;
	}
	
	public void setJid(JabberId jid) {
		this.jid = jid;
	}
	
	public String getNick() {
		return nick;
	}
	
	public void setNick(String nick) {
		this.nick = nick;
	}
	
	public Role getRole() {
		return role;
	}
	
	public void setRole(Role role) {
		this.role = role;
	}
	
}
